package util;

import domain.FileState;
import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UploadProgressUtil implements ProgressListener {
    private HttpSession session;
    public UploadProgressUtil(HttpServletRequest request){
        session = request.getSession();
        FileState state = new FileState();
        state.setFlag(false);
        session.setAttribute("fileState",state);
    }
    @Override
    public void update(long uploadedBytes, long totalBytes, int currentItem) {
        Object attribute = session.getAttribute("fileState");
        FileState fileState;
        if(attribute==null){
            fileState = new FileState();
            fileState.setFlag(false);
        }else{
            fileState = (FileState) attribute;
        }
        fileState.setUploadedBytes(uploadedBytes);
        fileState.setTotalBytes(totalBytes);
        fileState.setCurrentItem(currentItem);
//        System.out.println("uploadedBytes: "+uploadedBytes+",totalBytes: "+totalBytes+",currentItem: "+currentItem);
        if(uploadedBytes ==totalBytes){
            fileState.setFlag(true);
        }else{
            fileState.setFlag(false);
        }
        session.setAttribute("fileState",fileState);
    }
}
