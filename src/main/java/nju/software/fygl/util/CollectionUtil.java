package nju.software.fygl.util;

import java.util.List;

public class CollectionUtil {

	public static boolean listIsNull(List list) {
		if (list == null || list.size()==0) {
			return true;
		} else {
			return false;
		}
	}

}
