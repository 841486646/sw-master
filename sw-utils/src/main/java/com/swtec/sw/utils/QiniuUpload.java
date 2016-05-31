package com.swtec.sw.utils;

import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiniuUpload {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	String ACCESS_KEY = "_uaymqFOPI966FkDMM9Om7XnlmsEE6kPA2-AwtoY";
	String SECRET_KEY = "HkNepp1flORaRt-P3be7uFVcbu3LLkaKg8Ra4Y1x";
	// 要上传的空间
	String bucketname = "picture";
	// 上传文件的路径
	String filePath = "F://543f8058-9850-442f-a1f8-50390da9cf6b.png";

	// 密钥配置
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	// 创建上传对象
	UploadManager uploadManager = new UploadManager();

	// 覆盖上传
	public String getUpToken(String key) {
		// <bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
		// 如果希望只能上传指定key的文件，并且不允许修改，那么可以将下面的 insertOnly 属性值设为 1。
		// 第三个参数是token的过期时间
		return auth.uploadToken(bucketname, key, 3600,
				new StringMap().put("insertOnly", 1));
	}

	public void upload(String key) throws IOException {
		try {
			// 调用put方法上传，这里指定的key和上传策略中的key要一致
			Response res = uploadManager.put(filePath, key, getUpToken(key));
			// 打印返回的信息
			System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}
	/**
	 * 上传到七牛云
	 * @param file 上传的文件流
	 * @param key 上传到七牛后保存的文件名
	 * @throws IOException
	 */
	public void upload(byte[] file, String key) throws IOException {
		try {
			// 调用put方法上传，这里指定的key和上传策略中的key要一致
			Response res = uploadManager.put(file, key, getUpToken(key));
			// 打印返回的信息
			System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}
	public static void main(String[] args) throws IOException {
		new QiniuUpload().upload("mt/upload/543f8058-9850-442f-a1f8-50390da9cf6b11.png");
	}
}