package filters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Filters {
	/**
	 * 
	 * @param image
	 * @return
	 * Takes a BufferedImage and after appyling blur filter to it returns the BufferedImage.
	 */
	public static BufferedImage blur(BufferedImage image) {
        BufferedImage blurredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int height = 0; height < blurredImage.getHeight(); height++) {
            for (int width = 0; width < blurredImage.getWidth(); width++) {
                int redSum = 0, greenSum = 0, blueSum = 0;

                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        int x = width + dx;
                        int y = height + dy;

                        if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
                            Color color = new Color(image.getRGB(x, y));
                            redSum += color.getRed();
                            greenSum += color.getGreen();
                            blueSum += color.getBlue();
                        }
                    }
                }

                int numPixels = 9;
                int redAvg = redSum / numPixels;
                int greenAvg = greenSum / numPixels;
                int blueAvg = blueSum / numPixels;

                int rgb = (255 << 24) | (redAvg << 16) | (greenAvg << 8) | blueAvg;
                blurredImage.setRGB(width, height, rgb);
            }
        }

        return blurredImage;
    }
	/**
	 * 
	 * @param img
	 * @return
	 * Takes a BufferedImage and returns it after applying heavy blur to it.
	 */
	
	public static BufferedImage heavyBlur(BufferedImage img) {
        BufferedImage blurImg = new BufferedImage(img.getWidth() - 4, img.getHeight() - 4, BufferedImage.TYPE_INT_ARGB);
        
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < blurImg.getHeight(); y++) {
                for (int x = 0; x < blurImg.getWidth(); x++) {
                    int redSum = 0, greenSum = 0, blueSum = 0;

                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            int pixelX = x + dx + 3;
                            int pixelY = y + dy + 3;

                            if (pixelX >= 0 && pixelX < img.getWidth() && pixelY >= 0 && pixelY < img.getHeight()) {
                                Color color = new Color(img.getRGB(pixelX, pixelY));
                                redSum += 10 * color.getRed();
                                greenSum += 10 * color.getGreen();
                                blueSum += 10 * color.getBlue();
                            }
                        }
                    }

                    int numPixels = 9;
                    int redAvg = redSum / (10 * numPixels);
                    int greenAvg = greenSum / (10 * numPixels);
                    int blueAvg = blueSum / (10 * numPixels);

                    int rgb = (255 << 24) | (redAvg << 16) | (greenAvg << 8) | blueAvg;
                    blurImg.setRGB(x, y, rgb);
                }
            }
            img = blurImg;
        }
        
        return blurImg;
    }
	
	/**
	 * 
	 * @param img
	 * @return
	 * 
	 * Takes the BufferedImage and returns it after applying sharpen filter.
	 */
	public static BufferedImage sharpen(BufferedImage img) {
        BufferedImage sharpenedImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        float[] mask = {
            -1, -1, -1,
            -1, 9, -1,
            -1, -1, -1
        };
        
        for (int y = 1; y < img.getHeight() - 1; y++) {
            for (int x = 1; x < img.getWidth() - 1; x++) {
                int redSum = 0, greenSum = 0, blueSum = 0;
                
                int index = 0;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        Color color = new Color(img.getRGB(x + dx, y + dy));
                        redSum += color.getRed() * mask[index];
                        greenSum += color.getGreen() * mask[index];
                        blueSum += color.getBlue() * mask[index];
                        index++;
                    }
                }
                
                int red = Math.min(Math.max(redSum, 0), 255);
                int green = Math.min(Math.max(greenSum, 0), 255);
                int blue = Math.min(Math.max(blueSum, 0), 255);
                
                int rgb = (255 << 24) | (red << 16) | (green << 8) | blue;
                sharpenedImg.setRGB(x, y, rgb);
            }
        }
        
        return sharpenedImg;
    }
	
	/**
	 * 
	 * @param img
	 * @return
	 * 
	 * Takes the BufferedImage and returns it after applying heavy grayscale filter.
	 */
	public static BufferedImage heavyGrayScale (BufferedImage img) {
		BufferedImage grayImage = new BufferedImage(
			img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		int rgb=0, r=0, g=0, b=0;
		for (int y=0; y<img.getHeight(); y++) {
			for (int x=0; x<img.getWidth(); x++) {
				rgb = (int)(img.getRGB(x, y));
				r = ((rgb >> 16) & 0xFF);
				g = ((rgb >> 8) & 0xFF);
				b = (rgb & 0xFF);
				//rgb = (int)((r+g+b)/3); maybe this
				rgb = (int)(0.299 * r + 0.587 * g + 0.114 * b);
				rgb = (255<<24) | (rgb<<16) | (rgb<<8) | rgb;
				grayImage.setRGB(x,y,rgb);
			}
		}
		return grayImage;
	}
	/**
	 * 
	 * @param img
	 * @return
	 * Takes the BufferedImage and returns it after applying grayscale filter.
	 */
	public static BufferedImage grayScale (BufferedImage img) {
		BufferedImage grayImage = new BufferedImage(
			img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = grayImage.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return grayImage;
	}
	/**
	 * 
	 * @param img
	 * @return
	 * 
	 * Takes the BufferedImage and returns it after applying edge detection filter.
	 */
	public static BufferedImage detectEdges (BufferedImage img) {
		int h = img.getHeight(), w = img.getWidth(), threshold=30, p = 0;
		BufferedImage edgeImg = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		int[][] vert = new int[w][h];
		int[][] horiz = new int[w][h];
		int[][] edgeWeight = new int[w][h];
		for (int y=1; y<h-1; y++) {
			for (int x=1; x<w-1; x++) {
				vert[x][y] = (int)(img.getRGB(x+1, y-1)& 0xFF + 2*(img.getRGB(x+1, y)& 0xFF) + img.getRGB(x+1, y+1)& 0xFF
					- img.getRGB(x-1, y-1)& 0xFF - 2*(img.getRGB(x-1, y)& 0xFF) - img.getRGB(x-1, y+1)& 0xFF);
				horiz[x][y] = (int)(img.getRGB(x-1, y+1)& 0xFF + 2*(img.getRGB(x, y+1)& 0xFF) + img.getRGB(x+1, y+1)& 0xFF
					- img.getRGB(x-1, y-1)& 0xFF - 2*(img.getRGB(x, y-1)& 0xFF) - img.getRGB(x+1, y-1)& 0xFF);
				edgeWeight[x][y] = (int)(Math.sqrt(vert[x][y] * vert[x][y] + horiz[x][y] * horiz[x][y]));
				if (edgeWeight[x][y] > threshold)
					p = (255<<24) | (255<<16) | (255<<8) | 255;
				else 
					p = (255<<24) | (0<<16) | (0<<8) | 0; 
				edgeImg.setRGB(x,y,p);
			}
		}
		return edgeImg;
	}
	
	/**
	 * 
	 * @param image
	 * @param contrastPercentage
	 * @return
	 * 
	 * Takes the BufferedImage image and takes a integer contrastPercentage and applies contrast to the image
	 * at a choosen amount of contrastPercentage. Returns the BufferedImage
	 */
	public static BufferedImage contrast(BufferedImage image, int contrastPercentage) {
        double contrastFactor = (259.0 * (contrastPercentage + 255.0)) / (255.0 * (259.0 - contrastPercentage));

        BufferedImage adjustedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                int red = pixelColor.getRed();
                int green = pixelColor.getGreen();
                int blue = pixelColor.getBlue();

                int adjustedRed = (int) (contrastFactor * (red - 128) + 128);
                int adjustedGreen = (int) (contrastFactor * (green - 128) + 128);
                int adjustedBlue = (int) (contrastFactor * (blue - 128) + 128);

                adjustedRed = Math.max(0, Math.min(adjustedRed, 255)); // boundry check care
                adjustedGreen = Math.max(0, Math.min(adjustedGreen, 255));
                adjustedBlue = Math.max(0, Math.min(adjustedBlue, 255));

                Color adjustedColor = new Color(adjustedRed, adjustedGreen, adjustedBlue);
                adjustedImage.setRGB(x, y, adjustedColor.getRGB());
            }
        }

        return adjustedImage;
    }
	
	/**
	 * 
	 * @param img
	 * @param percentage
	 * @return
	 * Takes the BufferedImage image and takes a integer percentage and applies brighten to the image
	 * at a choosen amount of percentage. Returns the BufferedImage
	 */
	public static BufferedImage brighten (BufferedImage img, int percentage) {
		int r=0, g=0, b=0, rgb=0, p=0;
		int amount = (int)(percentage * 255 / 100);
		BufferedImage newImage = new BufferedImage(
			img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int y=0; y<img.getHeight(); y+=1) {
			for (int x=0; x<img.getWidth(); x+=1) {
				rgb = img.getRGB(x, y);
				r = ((rgb >> 16) & 0xFF) + amount;
				g = ((rgb >> 8) & 0xFF) + amount;
				b = (rgb & 0xFF) + amount;
				if (r>255) r=255;
				if (g>255) g=255;
				if (b>255) b=255;
				p = (255<<24) | (r<<16) | (g<<8) | b;
				newImage.setRGB(x,y,p);
			}
		}
		return newImage;
	}
}
