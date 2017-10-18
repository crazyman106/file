package feng.zi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Htm {

	public  void main(String[] args) throws ParseException {
		file2Bytes();
	}

	private void Htl() {
		String string = "<b>75757574</b> <img src=\"/i/eg_tulip.jpg\"  alt=\"上海鲜花港 - 郁金香\" />";
		Pattern pattern = Pattern.compile(">([\\d]+)<");// 匹配html字符
		Matcher matcher = pattern.matcher(string);
		if (matcher.find()) {
			matcher.reset();
			while (matcher.find()) {// 找到匹配的字符串
				System.out.println("hit: " + matcher.group(1));
			}
		}
	}

	private static void file2Bytes() {
		File file = new File("d:\\32.zip");
		try {
			InputStream inputStream = new FileInputStream(file);
			toByteArray(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static byte[] toByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
			out.write(buffer, 0, n);
		}
		OutputStream outputStream = new FileOutputStream(new File("d:\\32.txt"));
		outputStream.write(out.toByteArray());

		Decoder decoder = Base64.getDecoder();
		try {
			byte[] b = decoder.decode(out.toByteArray());
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			String imgFilePath = "d:\\base.zip";// 新生成的图片
			OutputStream outs = new FileOutputStream(imgFilePath);
			outs.write(b);
			outs.flush();
			outs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}
}
