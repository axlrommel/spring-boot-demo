package com.villagomezdiaz.common.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryScanner {
	private List<File> result = new ArrayList<File>();

	public static List<File> getSubFolders(String directory) throws Exception {
		List<File> subFolders = new ArrayList<File>();
		File d = new File(directory);
		if (!d.isDirectory()) {
			Exception e = new Exception(d.getAbsolutePath() + " is not a directory");
			throw e;
		}
		if (d.canRead()) {
			for (File temp : d.listFiles()) {
				subFolders.add(temp);
			}
		}
		return subFolders;
	}

	public DirectoryScanner(String directory, String fileType) throws Exception {
		getFilesFromFolder(directory, fileType);
	}

	public List<File> getResult() {
		return result;
	}

	public void getFilesFromFolder(String directory, String fileTypeToSearch) throws Exception {
		File d = new File(directory);
		if (!d.isDirectory()) {
			Exception e = new Exception(d.getAbsolutePath() + " is not a directory");
			throw e;
		}
		search(d, fileTypeToSearch);
	}

	private void search(File d, String fileTypeToSearch) throws Exception {
		if (d.isDirectory()) {
			if (d.canRead()) {
				for (File temp : d.listFiles()) {
					if (temp.isDirectory()) {
						search(temp, fileTypeToSearch);
					} else {
						if (fileTypeToSearch.equals("*")) {
							result.add(temp);
						} else if (temp.getName().endsWith(fileTypeToSearch)) {
							result.add(temp);
						}

					}
				}

			} else {
				Exception e = new Exception(d + " don't have permission");
				throw e;
			}
		}
	}
}
