package test.bsv5;

import java.util.ArrayList;

import org.wiegand.at8000.WgUdpCommShort;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.tdk3A.authorization.entity.TBControllerEntity;

public class Bsv5Test {

public static void main(String[] args) {
	// 前台获得的tbcontrollerentity集合（前台加载控制器列表附带用到信息）
	ArrayList<TBControllerEntity> tbcon = new ArrayList<TBControllerEntity>();
	//卡号信息(取后七位)
	String cardno = "5221871";
	
	long card_no = Long.parseLong(cardno);
	// 测试实例
	//本案例中测试说明
	//控制器SN  = 229999901
	//控制器IP  = 192.168.168.123
	//电脑  IP  = 192.168.168.101
	//用于作为接收服务器的IP (本电脑IP 192.168.168.101), 接收服务器端口 (61005)
	long controllerSN;
	String controllerIP;
	//接收服务器设置在jeesite.properties的watchServerIP属性，程序从properties获取
	String watchServerIP = Global.getConfig("watchServerIP");
	int watchServerPort= Integer.parseInt(Global.getConfig("watchServerPort"));
	//循环控制器信息
	for (int i = 0; i < tbcon.size(); i++) {
		controllerSN = Long.parseLong(tbcon.get(i).getfControllersn());
		controllerIP = tbcon.get(i).getfIp();
		addAlert(card_no,controllerSN,controllerIP,watchServerIP,watchServerPort);
	}

}


public static String addAlert(long card_no,long controllerSN,String controllerIP,String watchServerIP,int watchServerPort) {
	        // TODO 自动生成的方法存根
			byte[] recvBuff;
			int success =0;
			WgUdpCommShort pkt = new WgUdpCommShort();
			pkt.iDevSn = controllerSN;
			//log(String.format("控制器SN = %d \r\n", controllerSN));
			//打开udp连接
			pkt.CommOpen(controllerIP);
			//1.11	权限添加或修改[功能号: 0x50] **********************************************************************************
			//增加卡号0D D7 37 00, 通过当前控制器的所有门
			pkt.Reset();
			pkt.functionID = (byte) 0x50;
			pkt.iDevSn = controllerSN; 
			//0D D7 37 00 要添加或修改的权限中的卡号 = 0x0037D70D = 3659533 (十进制)
			long cardNOOfPrivilege =card_no;
			//memcpy(&(pkt.data[0]), &cardNOOfPrivilege, 4);
			System.arraycopy(WgUdpCommShort.longToByte(cardNOOfPrivilege) , 0, pkt.data, 0, 4);
			//20 10 01 01 起始日期:  2010年01月01日   (必须大于2001年)
			pkt.data[4] = 0x20;
			pkt.data[5] = 0x10;
			pkt.data[6] = 0x01;
			pkt.data[7] = 0x01;
			//20 29 12 31 截止日期:  2029年12月31日
			pkt.data[8] = 0x20;
			pkt.data[9] = 0x29;
			pkt.data[10] = 0x12;
			pkt.data[11] = 0x31;
			//01 允许通过 一号门 [对单门, 双门, 四门控制器有效] 
			pkt.data[12] = 0x01;
			//01 允许通过 二号门 [对双门, 四门控制器有效]
			pkt.data[13] = 0x01;  //如果禁止2号门, 则只要设为 0x00
			//01 允许通过 三号门 [对四门控制器有效]
			pkt.data[14] = 0x01;
			//01 允许通过 四号门 [对四门控制器有效]
			pkt.data[15] = 0x01;

			recvBuff = pkt.run();
			success =0;
			if (recvBuff != null)
			{
				if (WgUdpCommShort.getIntByByte(recvBuff[8]) == 1)
				{
					//这时 刷卡号为= 0x0037D70D = 3659533 (十进制)的卡, 1号门继电器动作.
				//	log("1.11 权限添加或修改	 成功...");
					success =1;
				}
			}
			//关闭udp连接
			pkt.CommClose();
	        return "success";
}


public static String deleteAlert(long card_no,long controllerSN,String controllerIP,String watchServerIP,int watchServerPort) {	// TODO 自动生成的方法存根
	byte[] recvBuff;
	int success =0;
	WgUdpCommShort pkt = new WgUdpCommShort();
	pkt.iDevSn = controllerSN;
	//打开udp连接
	pkt.CommOpen(controllerIP);
	//1.12	权限删除(单个删除)[功能号: 0x52] **********************************************************************************
	pkt.Reset();
	pkt.functionID = (byte) 0x52;
	pkt.iDevSn = controllerSN; 
	//要删除的权限卡号0D D7 37 00  = 0x0037D70D = 3659533 (十进制)
	long cardNOOfPrivilegeToDelete =card_no;
	System.arraycopy(WgUdpCommShort.longToByte(cardNOOfPrivilegeToDelete) , 0, pkt.data, 0, 4);

	recvBuff = pkt.run();
	success =0;
	if (recvBuff != null)
	{
		if (WgUdpCommShort.getIntByByte(recvBuff[8]) == 1)
		{
			//这时 刷卡号为= 0x0037D70D = 3659533 (十进制)的卡, 1号门继电器不会动作.
			success =1;
		}
	}
	pkt.CommClose();
	 return "success";	
}
}
