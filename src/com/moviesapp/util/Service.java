/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moviesapp.util;

import com.moviesapp.global.AppConfigConst;
import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Oumayma
 */
public class Service {
    public static  void copyImage(URI uri, String name){
        Path from = Paths.get(uri);
        Path to   = Paths.get(AppConfigConst.DIST+name);
        CopyOption[] options = new CopyOption[]{
            StandardCopyOption.COPY_ATTRIBUTES
        };
        try{
            Files.copy(from, to, options);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
