import cipher.Des;
import filka.core.CipherService;

module DES{

    requires mainapp;

    uses CipherService;
    provides CipherService with Des;

}