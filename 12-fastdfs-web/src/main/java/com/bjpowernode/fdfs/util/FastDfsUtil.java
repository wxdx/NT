package com.bjpowernode.fdfs.util;

import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * ClassName:FastDfsUtil
 * Package:com.bjpowernode.fdfs.util
 * Description:
 *
 * @Date:2018/10/13 16:15
 * @Author:hiwangxiaodong@hotmail.com
 */
public class FastDfsUtil {
    public static String[] upload(byte[] file, String extFileName){
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        String[] result = null;
        try {
            ClientGlobal.init("fastdfs-client.conf");
            TrackerClient tc = new TrackerClient();
            trackerServer = tc.getConnection();
            storageServer = tc.getStoreStorage(trackerServer);

            StorageClient sc = new StorageClient(trackerServer, storageServer);

             result = sc.upload_file(file, extFileName,null);



        } catch (Exception e) {
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
        return result;
    }
    public static byte[] download(String groupName, String remoteFileName){

        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            ClientGlobal.init("fastdfs-client.conf");
            TrackerClient tc = new TrackerClient();
            trackerServer = tc.getConnection();
            storageServer = tc.getStoreStorage(trackerServer);

            StorageClient sc = new StorageClient(trackerServer, storageServer);

            byte[] bytes = sc.download_file(groupName, remoteFileName);

            return bytes;


        } catch (Exception e) {
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
        return null;

    }
    public static int delete(String groupName, String remoteFileName){
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        int delete = -1;
        try {
            ClientGlobal.init("fastdfs-client.conf");
            TrackerClient tc = new TrackerClient();
            trackerServer = tc.getConnection();
            storageServer = tc.getStoreStorage(trackerServer);

            StorageClient sc = new StorageClient(trackerServer, storageServer);

            delete = sc.delete_file(groupName, remoteFileName);

        } catch (Exception e) {
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
        return delete;
    }
}
