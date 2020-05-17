package net.informaticalibera.videoediting;


/**
 *  <p>
 *  This native interface is deprecated because you shouldn't use it
 *  directly.</p>
 *  <p>
 *  This native interface provides methods to get some info of the video placed
 *  in the {@link com.codename1.io.FileSystemStorage} and to optimize them for
 *  fast uploading to a server.</p>
 *  <p>
 *  About the video compression, it relies on
 *  <a href="https://github.com/tanersener/mobile-ffmpeg">MobileFFmpeg</a>
 *  on Android and on AVAssetExportSession on iOS.
 * 
 *  @author Francesco Galgani
 */
@java.lang.Deprecated
public interface VideoEditingNativeInterface extends com.codename1.system.NativeInterface {

	/**
	 *  Extract the first frame from the given video file and saves it to the
	 *  given jpeg file.
	 * 
	 *  @param videoFile FileSystemStorage path, make sure doesn't include the
	 *  "file:" prefix
	 *  @param jpegFile FileSystemStorage path, make sure doesn't include the
	 *  "file:" prefix
	 */
	public void getImageFromVideo(String videoFile, String jpegFile);

	/**
	 *  Returns the duration of the videoFile in seconds, or -1 in case of error
	 * 
	 *  @param videoFile FileSystemStorage path, make sure doesn't include the
	 *  "file:" prefix
	 *  @return duration is seconds (or -1 in case of error)
	 */
	public long getVideoDuration(String videoFile);

	/**
	 *  Get the average bitrate (in bits/sec), if available.
	 * 
	 *  @param videoFile FileSystemStorage path, make sure doesn't include the
	 *  "file:" prefix
	 *  @return average bitrate (in bits/sec), if available (or -1 in case of
	 *  error).
	 */
	public int getVideoBitrate(String videoFile);

	/**
	 *  Returns the size of the given video in the format widthxheight, for
	 *  example 1280x720
	 * 
	 *  @param videoFile FileSystemStorage path, make sure doesn't include the
	 *  "file:" prefix
	 *  @return the size of the video (or null in case of error)
	 */
	public String getVideoSize(String videoFile);

	/**
	 *  (Async) method to optimize the given inputVideoFile for fast
	 *  uploading.Currently it uses a default low quality both on Android and
	 *  iOS.
	 * 
	 *  @param inputVideoFile FileSystemStorage path, make sure doesn't include
	 *  the "file:" prefix
	 *  @param outputVideoFile FileSystemStorage path, make sure doesn't include
	 *  the "file:" prefix (the file name defaults to "optimized.mp4")
	 */
	public void optimizeVideoForUpload(String inputVideoFile, String outputVideoFile);
}
