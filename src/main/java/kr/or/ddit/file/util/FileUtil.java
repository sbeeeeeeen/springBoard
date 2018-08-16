package kr.or.ddit.file.util;

public class FileUtil {
	
//	public final static String fileUploadPath = "D:\\A_TeachingMaterial\\7.JspSpring\\fileUpload";
	public final static String fileUploadPath = "C:\\ddit\\W\\A_TeachingMaterial\\7.JspSpring\\fileUpload";

	public static String getFileName(String contentDisposition){
		String[] temp = contentDisposition.split("; ");
		String result = "";
		for(String str : temp){
			if(str.contains("filename")){
				result = str.substring(str.indexOf("=\"")+2, str.length()-1);
			}
		}
		return result;
	}
}
