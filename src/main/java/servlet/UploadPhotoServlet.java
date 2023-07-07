package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadPhotoServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		
		String filename = null;
		
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			factory.setSizeThreshold(1024*1024*20); //limited 20M
			
			 List items = upload.parseRequest(req);
			 
			 Iterator iter = items.iterator();
			 while(iter.hasNext()) {
				 FileItem item = (FileItem) iter.next();
				 if(!item.isFormField()) {
					filename = System.currentTimeMillis()+".jpg";
					String photoFolder = req.getServletContext().getRealPath("uploaded");
					
					File f = new File(photoFolder,filename);
					f.getParentFile().mkdirs();
					
					InputStream is = item.getInputStream();
					
					FileOutputStream fos = new FileOutputStream(f);
					byte b[] = new byte[1024*1024*20];
					int length = 0;
					while((length = is.read(b)) != -1) {
						fos.write(b,0,length);
					}
					fos.close();
				 }else {
					 System.out.println(item.getFieldName());
					 String value = item.getString();
					 value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
	                 System.out.println(value);
				 }
			 }
			 
			 String html = "<img width='200' height='150' src='uploaded/%s' />";
			 res.setContentType("text/html");
			 PrintWriter pw = res.getWriter();
			 pw.format(html, filename);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
