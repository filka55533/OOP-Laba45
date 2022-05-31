import filka.core.CipherService;
import ciphering.AES;
module AES {

    requires mainapp;

    uses CipherService;
    provides CipherService with AES;
}