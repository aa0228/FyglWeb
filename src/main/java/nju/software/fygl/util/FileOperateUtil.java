package nju.software.fygl.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class FileOperateUtil {

	/**
	 * 下载文件
	 * 
	 * @param request
	 * @param response
	 * @param downLoadPath
	 * @param contentType
	 * @param realName
	 * @throws Exception
	 */
	public static void download(HttpServletRequest request, // 不知道为啥在IE下面下载速度巨慢。。。。以后再来解决这个问题。。😢
			HttpServletResponse response, String downLoadPath, String contentType, String realName)
					throws Exception
	{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		long fileLength = new File(downLoadPath).length();
		System.out.println("downLoadPath:" + downLoadPath);

		// response.setContentType(contentType);
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String(realName.getBytes("GB2312"), "ISO-8859-1"));// 注意此处必须是GB2312不然IE会是乱码，这个问题在我解决之前居然存在了辣么久没人解决，到底都是在干吗，🐴了个🐔
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}

	public static boolean copyFile(String srcFileName, String destFileName, boolean overlay)
	{
		File srcFile = new File(srcFileName);
		String MESSAGE = "";
		// 判断源文件是否存在
		if(!srcFile.exists()) {
			MESSAGE = "源文件：" + srcFileName + "不存在！";
			JOptionPane.showMessageDialog(null, MESSAGE);
			return false;
		}
		else if(!srcFile.isFile()) {
			MESSAGE = "复制文件失败，源文件：" + srcFileName + "不是一个文件！";
			JOptionPane.showMessageDialog(null, MESSAGE);
			return false;
		}

		// 判断目标文件是否存在
		File destFile = new File(destFileName);
		if(destFile.exists()) {
			// 如果目标文件存在并允许覆盖
			if(overlay) {
				// 删除已经存在的目标文件，无论目标文件是目录还是单个文件
				new File(destFileName).delete();
			}
		}
		else {
			// 如果目标文件所在目录不存在，则创建目录
			if(!destFile.getParentFile().exists()) {
				// 目标文件所在目录不存在
				if(!destFile.getParentFile().mkdirs()) {
					// 复制文件失败：创建目标文件所在目录失败
					return false;
				}
			}
		}

		// 复制文件
		int byteread = 0; // 读取的字节数
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];

			while((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
			return true;
		}
		catch(FileNotFoundException e) {
			return false;
		}
		catch(IOException e) {
			return false;
		}
		finally {
			try {
				if(out != null)
					out.close();
				if(in != null)
					in.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean DeleteFolder(String sPath)
	{
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if(!file.exists()) { // 不存在返回 false
			return flag;
		}
		else {
			// 判断是否为文件
			if(file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			}
			else { // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}

	public static boolean deleteDirectory(String sPath)
	{
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if(!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if(!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for(int i = 0; i < files.length; i++) {
			// 删除子文件
			if(files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if(!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if(!flag)
					break;
			}
		}
		if(!flag)
			return false;
		// 删除当前目录
		if(dirFile.delete()) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean deleteFile(String sPath)
	{
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if(file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
	
	public static boolean rename(String fromDir, String toDir)
	{
		File from = new File(fromDir);

	    if (!from.exists() || !from.isDirectory()) {
	      System.out.println("Directory does not exist: " + fromDir);
	      return false;
	    }
	    File to = new File(toDir);
	    //Rename
	    if (from.renameTo(to)){
	      return true;
	    }
	    else{
	      return false;
	    }
	}
}
