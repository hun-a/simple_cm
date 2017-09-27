package com.cubrid.common.ui.er.utils;

import java.util.Arrays;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;

import com.cubrid.common.ui.er.layout.ERTableNode;

public class LayoutUtil {
	public static void unionAndExpand(Rectangle basicRec, Rectangle rect) {
		int startX = Math.min(basicRec.x, rect.x);
		int startY = Math.min(basicRec.y, rect.y);

		int rightDownX = Math.max(basicRec.x + basicRec.width, rect.x
				+ rect.width);
		int rightDownY = Math.max(basicRec.y + basicRec.height, rect.y
				+ rect.height);

		basicRec.x = startX;
		basicRec.y = startY;
		basicRec.width = rightDownX - startX;
		basicRec.height = rightDownY - startY;
	}

	public static double getCenterNumber(double a, double b, double c) {
		double[] nums = { a, b, c };
		Arrays.sort(nums);
		return nums[1];
	}

	public static void splitSimpleTwoHalf(List allNode, List leftNodes,
			List rightNodes) {

		int count = allNode.size();
		int half = count / 2;
		for (int i = 0; i < half; i++) {
			leftNodes.add(allNode.get(i));
		}
		for (int i = half; i < count; i++) {
			rightNodes.add(allNode.get(i));
		}
	}

	public static void splitSmartTwoHalf(List<ERTableNode> allNode,
			List<ERTableNode> leftNodes, List<ERTableNode> rightNodes) {

		int count = allNode.size();
		int half = count / 2;
		for (int i = 0; i < half; i++) {
			leftNodes.add(allNode.get(i));
		}
		for (int i = half; i < count; i++) {
			rightNodes.add(allNode.get(i));
		}
	}
}
