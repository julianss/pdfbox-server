package pdfboxserver;

public class PDFBoxServer{
    public static void main(String[] args){
        MultiThreadedServer server = new MultiThreadedServer(9998);
        new Thread(server).start();

        while(true){
            try {
                Thread.sleep(20 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println("Stopping Server");
        server.stop();
    }
}
