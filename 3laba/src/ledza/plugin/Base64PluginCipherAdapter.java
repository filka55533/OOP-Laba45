package ledza.plugin;

public class Base64PluginCipherAdapter extends BasicCipherAdapter{

    public Base64PluginCipherAdapter(){

        super(new Base64Plugin());

    }


    public static void main(String ... args){

        Base64PluginCipherAdapter a = new Base64PluginCipherAdapter();
        a.encryptFile("bsuir.txt");
        a.decryptFile("bsuir.txt");

    }

}