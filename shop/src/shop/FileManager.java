package shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {

	private FileWriter fw = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	private File item = null;
	private File user = null;

	private String fileItem = "Item.txt";
	private String fileUser = "User.txt";
	private String filePathItem = System.getProperty("user.home");
	private String filePathUser = System.getProperty("user.home");
	private String sep = System.getProperty("file.separator");

	private FileManager() {
		filePathItem += sep + "desktop" + sep + fileItem;
		filePathUser += sep + "desktop" + sep + fileUser;
		item = new File(filePathItem);
		user = new File(filePathUser);
	}

	private static FileManager instance = new FileManager();

	public static FileManager getInstance() {
		return instance;
	}

	public boolean isExsistItemFile() {
		return item.exists();
	}

	public boolean isExsistUserFile() {
		return user.exists();
	}

	public void autoSaveUserList(String textUser) {
		try {
			fw = new FileWriter(user);
			fw.write(textUser);
			fw.close();

			System.out.println("유저 데이터 파일 저장 성공");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("유저 데이터 파일 저장 실패");
			e.printStackTrace();
		}
	}

	public void autoSaveItemList(String textItem) {
		try {
			fw = new FileWriter(item);
			fw.write(textItem);
			fw.close();

			System.out.println("아이템 데이터 파일 저장 성공");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("아이템 데이터 파일 저장 실패");
			e.printStackTrace();
		}
	}

	public String autoLoadUserData() {
		String loadData = "";
		try {
			fr = new FileReader(user);
			br = new BufferedReader(fr);

			while (br.ready())
				loadData += br.readLine() + "\n";

			br.close();
			fr.close();
			System.out.println("유저 데이터 로드 성공");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("유저 데이터 로드 실패");
		}

		return loadData;
	}

	public String autoLoadItemData() {
		String loadData = "";
		try {
			fr = new FileReader(item);
			br = new BufferedReader(fr);

			while (br.ready())
				loadData += br.readLine() + "\n";

			br.close();
			fr.close();
			System.out.println("아이템 데이터 로드 성공");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("아이템 데이터 로드 실패");
		}

		return loadData;
	}
}
