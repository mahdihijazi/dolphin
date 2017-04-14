package org.dolphinemu.dolphinemu.utils;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.RequestHandler;

import org.dolphinemu.dolphinemu.NativeLibrary;

import java.io.IOException;
import java.nio.IntBuffer;

public class GameBannerRequestHandler extends RequestHandler {
	@Override
	public boolean canHandleRequest(Request data) {
		return "iso".equals(data.uri.getScheme());
	}

	@Override
	public Result load(Request request, int networkPolicy) throws IOException {
		String url = request.uri.getHost() + request.uri.getPath();
		int[] vector = NativeLibrary.GetBanner(url);
		int width = 96;
		int height = 32;
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

//		premultiply(vector);
//		bitmap.copyPixelsFromBuffer(IntBuffer.wrap(vector));

		bitmap.setPixels(vector, 0, width, 0, 0, width, height);
		return new Result(bitmap, Picasso.LoadedFrom.DISK);
	}

	private void premultiply(int[] colors) {
		for (int i = 0; i < colors.length; i++) {
			int r = Color.red(colors[i]);
			int g = Color.green(colors[i]);
			int b = Color.blue(colors[i]);
			int a = Color.alpha(colors[i]);
			int normalizedAlpha = a / 255;
			r = r * normalizedAlpha;
			g = g * normalizedAlpha;
			b = b * normalizedAlpha;
			colors[i] = Color.argb(a, r, g, b);
		}
	}
}
