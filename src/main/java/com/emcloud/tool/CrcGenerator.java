package com.emcloud.tool;

import java.math.BigInteger;

/**
 * CRC16 校验码生成器， 用于emcloud项目中生成下发命令.
 */
public class CrcGenerator {


    public CrcGenerator() {
    }

    public static void main(String[] args) {
        //050600010000D98E
        //050600020001E84E
        //{"off":"01100005000102000167C5","on":"01100005000102000227C4"}
//        021000050001020001
        //021000050001020000

//        byte[] bs={1,16,0,5,0,1,2,0,1}; //c567  01100005000102000167C5
//        byte[] bs={1,16,0,5,0,1,2,0,0};//05A6  011000050001020000A605
//
//        byte[] bs={1,16,0,3,0,1,2,0,50};
//        byte[] bs={2,16,0,5,0,1,2,0,0};//f5b2  021000050001020000B2F5
//        byte[] bs={2,16,0,5,0,1,2,0,1};//3573  0210000500010200007335
        byte[] bs={51,16,0,5,0,1,2,0,0};//64eb  511000050001020000EB64
//        byte[] bs={51,16,0,5,0,1,2,0,1};//a42a  5110000500010200002AA4

        int crc2 =getCrc16(bs);
        System.out.println(Integer.toHexString(crc2));
    }

    public static int getCrc16(byte[] arr_buff) {
        int len = arr_buff.length;

        //预置 1 个 16 位的寄存器为十六进制FFFF, 称此寄存器为 CRC寄存器。
        int crc = 0xFFFF;
        int i, j;
        for (i = 0; i < len; i++)

        {
            //把第一个 8 位二进制数据 与 16 位的 CRC寄存器的低 8 位相异或, 把结果放于 CRC寄存器
            crc = ((crc & 0xFF00) | (crc & 0x00FF) ^ (arr_buff[i] & 0xFF));
            for (j = 0; j < 8; j++) {
                //把 CRC 寄存器的内容右移一位( 朝低位)用 0 填补最高位, 并检查右移后的移出位
                if ((crc & 0x0001) > 0) {
                    //如果移出位为 1, CRC寄存器与多项式A001进行异或
                    crc = crc >> 1;
                    crc = crc ^ 0xA001;
                } else
                    //如果移出位为 0,再次右移一位
                    crc = crc >> 1;
            }
        }
        return crc;
    }





}
