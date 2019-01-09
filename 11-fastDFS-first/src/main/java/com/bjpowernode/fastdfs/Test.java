package com.bjpowernode.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * ClassName:Test
 * Package:com.bjpowernode.fastdfs
 * Description:
 *
 * @Date:2018/10/12 16:06
 * @Author:hiwangxiaodong@hotmail.com
 */
public class Test {
    public static void main(String[] args) {
        upload();
        //download();
        //delete();
    }

    public static void upload(){
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            TrackerClient tc = new TrackerClient();
            trackerServer = tc.getConnection();
            storageServer = tc.getStoreStorage(trackerServer);

            StorageClient sc = new StorageClient(trackerServer, storageServer);

            String[] result = sc.upload_file("E:\\wallhaven-685393.jpg", "jpg", null);

            for (String s : result) {
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            if (storageServer != null){
                try {
                    storageServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (trackerServer != null){
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void download(){

        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            TrackerClient tc = new TrackerClient();
            trackerServer = tc.getConnection();
            storageServer = tc.getStoreStorage(trackerServer);

            StorageClient sc = new StorageClient(trackerServer, storageServer);

            /**
             * 0 代表下载成功
             * 22代表路径错误
             * 2代表文件找不到
             *
             */
            int resultCode = sc.download_file("group1", "M00/00/00/wKhsglvAWxqAKKY4BOpcmGxMOac383.avi", "E://a.avi");

            System.out.println(resultCode);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            if (storageServer != null){
                try {
                    storageServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (trackerServer != null){
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static void delete(){
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            TrackerClient tc = new TrackerClient();
            trackerServer = tc.getConnection();
            storageServer = tc.getStoreStorage(trackerServer);

            StorageClient sc = new StorageClient(trackerServer, storageServer);

            int resultCode = sc.delete_file("group1", "M00/00/00/wKhsglvAWxqAKKY4BOpcmGxMOac383.avi");

            System.out.println(resultCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            if (storageServer != null){
                try {
                    storageServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (trackerServer != null){
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
