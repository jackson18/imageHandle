package com.qijiabin.imageHandle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * ========================================================
 * 日 期：2016年10月11日 上午11:36:08
 * 版 本：1.0.0
 * 类说明：
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
public class ThumbnailUtil {

	/**
	 * 指定大小进行缩放
	 * 
	 * size(width,height) 若图片横比200小，高比300小，不变
	 * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
	 * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
	 * @param sourceFile
	 * @param width
	 * @param height
	 * @param destFile
	 */
	public static void scaleToSize(File sourceFile, File destFile, int width, int height) {
		try {
			Thumbnails.of(sourceFile).size(width, height).toFile(destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 按照比例进行缩放
	 * scale(比例)
	 */
	public static void scaleToRatio(File sourceFile, File destFile, float scale) {
		try {
			Thumbnails.of(sourceFile).scale(scale).toFile(destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 不按照比例，指定大小进行缩放
	 * keepAspectRatio(false) 默认是按照比例缩放的
	 */
	public static void scaleToFixSize(File sourceFile, File destFile, int width, int height) {
		try {
			Thumbnails.of(sourceFile).size(width, height).keepAspectRatio(false).toFile(destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 旋转
	 * rotate(角度),正数：顺时针 负数：逆时针
	 */
	public static void rotate(File sourceFile, File destFile, int width, int height, double rotate) {
		try {
			Thumbnails.of(sourceFile).size(width, height).rotate(rotate).toFile(destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 水印
	 * watermark(位置，水印图，透明度)
	 */
	public static void watermark(File sourceFile, File destFile, File watermarkFile, int width, int height, float pellucidity) {
		try {
			Thumbnails.of(sourceFile)
			.size(width, height)
			.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(watermarkFile), 0.5f)
			.outputQuality(0.8f)
			.toFile(destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 裁剪
	 */
	public static void cut(File sourceFile, File destFile, int width, int height) {
		// 图片中心400*400的区域
		try {
			Thumbnails.of(sourceFile).sourceRegion(Positions.CENTER, 400,400).size(width, height).keepAspectRatio(false).toFile(destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*// 图片右下400*400的区域
		Thumbnails.of("images/test.jpg").sourceRegion(Positions.BOTTOM_RIGHT,
				400, 400).size(200, 200).keepAspectRatio(false).toFile(
				"C:/image_region_bootom_right.jpg");
		// 指定坐标
		Thumbnails.of("images/test.jpg").sourceRegion(600, 500, 400, 400).size(
				200, 200).keepAspectRatio(false).toFile(
				"C:/image_region_coord.jpg");*/
	}

	/**
	 * 转化图像格式
	 * outputFormat(图像格式)
	 */
	public static void format(File sourceFile, File destFile, int width, int height, String format) {
		try {
			Thumbnails.of(sourceFile).size(width, height).outputFormat(format).toFile(destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出到OutputStream
	 * toOutputStream(流对象)
	 */
	public static void outputStream(File sourceFile, File destFile, int width, int height) {
		try {
			Thumbnails.of(sourceFile).size(width, height).toOutputStream(new FileOutputStream(destFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出到BufferedImage
	 * asBufferedImage() 返回BufferedImage
	 */
	public static void outputBufferedImage(File sourceFile, File destFile, int width, int height, String formatName) {
		try {
			BufferedImage thumbnail = Thumbnails.of(sourceFile).size(width, height).asBufferedImage();
			ImageIO.write(thumbnail, formatName, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		ThumbnailUtil.scaleToSize(new File("F:/e.JPG"), new File("F:/scaleToSize.JPG"), 100, 100);
		ThumbnailUtil.scaleToRatio(new File("F:/e.JPG"), new File("F:/scaleToRatio.JPG"), 0.25f);
		ThumbnailUtil.scaleToFixSize(new File("F:/e.JPG"), new File("F:/scaleToFixSize.JPG"), 250, 250);
		ThumbnailUtil.rotate(new File("F:/e.JPG"), new File("F:/rotate.JPG"), 250, 250, 90);
		ThumbnailUtil.watermark(new File("F:/b5.jpg"), new File("F:/watermark.JPG"), new File("F:/d.jpg"), 1000, 800, 0.8f);
		ThumbnailUtil.cut(new File("F:/e.JPG"), new File("F:/cut.JPG"), 300, 250);
		ThumbnailUtil.format(new File("F:/e.JPG"), new File("F:/format.png"), 300, 250, "png");
		ThumbnailUtil.outputStream(new File("F:/e.JPG"), new File("F:/outputStream.png"), 300, 250);
		ThumbnailUtil.outputBufferedImage(new File("F:/e.JPG"), new File("F:/outputBufferedImage.png"), 300, 250, "png");
	}
	
}

