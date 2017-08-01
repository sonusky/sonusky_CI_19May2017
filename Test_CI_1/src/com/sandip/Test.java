package com.sandip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class Test {

	public Test() {
	}

	public static void main(String[] args) {
		String user = "bmg_cwr";
		String password = "N905S1I0Br3D";
		String host = "ftp.bmg.com";
		int port=22;

		String remoteFile="sample.txt";

		try
		{
			JSch jsch = new JSch(); 
			Session session = jsch.getSession(user, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("Establishing Connection...");
			session.connect();
			System.out.println("Connection established.");
			System.out.println("Creating SFTP Channel.");
			ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
			sftpChannel.connect();
			System.out.println("SFTP Channel created.");
			
			//sftpChannel.cd(SFTPWORKINGDIR);
			
			InputStream out= null;
			out= sftpChannel.get("*.xlsx");
			BufferedReader br = new BufferedReader(new InputStreamReader(out));
			String line;
			while ((line = br.readLine()) != null) 
			{
				System.out.println(line);
			}
			br.close();
			sftpChannel.disconnect();
			session.disconnect();
		}
		catch(JSchException | SftpException | IOException e)
		{
			System.out.println(e);
		}

	}

}
