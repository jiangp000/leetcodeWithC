package domain;

public class FileState {

    private long uploadedBytes = 0L;  // 已经上传的字节数
    private long totalBytes = 0L;   // 所有文件总字节数
    private int currentItem = 0;    // 当前上传的第几个文件
    private long startTime = System.currentTimeMillis();    // 开始上传时间
    private boolean flag;   // 是否上传完成

    public long getUploadedBytes() {
        return uploadedBytes;
    }

    public void setUploadedBytes(long uploadedBytes) {
        this.uploadedBytes = uploadedBytes;
    }

    public long getTotalBytes() {
        return totalBytes;
    }

    public void setTotalBytes(long totalBytes) {
        this.totalBytes = totalBytes;
    }

    public int getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}

