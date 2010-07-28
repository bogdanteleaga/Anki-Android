package com.ichi2.anki;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Download extends HashMap<String,Object>{

	public static final String TAG = "AnkiDroid";

	// Status names
    public static final String STATUSES[] = {"Downloading", "Paused", "Complete", "Cancelled", "Error"};
    
    // Status codes
    public static final int START = -1;
    public static final int DOWNLOADING = 0;
    public static final int PAUSED = 1;
    public static final int COMPLETE = 2;
    public static final int CANCELLED = 3;
    public static final int ERROR = 4;
    
    // Download URL
	private URL url;
	// Size of download in bytes
    protected long size;
    // Number of bytes downloaded
    private long downloaded;
    // Current status of download
    private int status; 
    private int progress;
    // Download's title
    protected String title;
    
    // Constructor for Download.
    public Download(URL url) {
    	this.url = url;
    	put("filename", url.toString());
        size = -1;
        downloaded = 0;
        status = START;
    }
    
    public Download(String title)
    {
    	this.title = title;
    	size = -1;
    	downloaded = 0;
    	status = START;
    }
    
    public Download(String title, long downloaded)
    {
    	this.title = title;
    	this.downloaded = downloaded;
    	status = START;
    }
    
    public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
		put("filename", url.toString());
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
		float sizeToShow = size;
		int divs = 0;
		while(sizeToShow > 1000)
		{
			sizeToShow = sizeToShow / 1000;
			divs++;
		}
		
		DecimalFormat dec = new DecimalFormat("#.##");
		switch(divs)
		{
			case 0:
				put("size", dec.format(sizeToShow) + "B");
				break;
				
			case 1:
				put("size", dec.format(sizeToShow) + "KB");
				break;
				
			case 2:
				put("size", dec.format(sizeToShow) + "MB");
				break;
				
			case 3:
				put("size", dec.format(sizeToShow) + "GB");
				break;
		}
		setProgress();
	}

	public long getDownloaded() {
		return downloaded;
	}

	public void setDownloaded(long downloaded) {
		this.downloaded = downloaded;
		float downloadedToShow = downloaded;
		int divs = 0;
		while(downloadedToShow > 1000)
		{
			downloadedToShow = downloadedToShow / 1000;
			divs++;
		}
		
		DecimalFormat dec = new DecimalFormat("#.##");
		switch(divs)
		{
			case 0:
				put("downloaded", dec.format(downloadedToShow) + "B");
				break;
				
			case 1:
				put("downloaded", dec.format(downloadedToShow) + "KB");
				break;
				
			case 2:
				put("downloaded", dec.format(downloadedToShow) + "MB");
				break;
				
			case 3:
				put("downloaded", dec.format(downloadedToShow) + "GB");
				break;
		}
		setProgress();
	}

	public int getProgress() 
	{
		return progress;
	}
	
	private void setProgress() 
	{
		progress = (int) (((float)downloaded / size) * 100);
		put("progress", progress + "%");
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
