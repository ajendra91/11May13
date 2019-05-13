package com.example.FileUpload2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmpController {

    public Path path = Paths.get("src/main/resources/static");

    public List<Emp> lst=new ArrayList<Emp>();

    @RequestMapping("/")
    public String index(){
        return "view/index";
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String hello(@RequestParam int id, @RequestParam String name, @RequestParam String age,
                        @RequestParam MultipartFile myfile)throws IOException {

        Files.createDirectories(path);
        String filename= StringUtils.cleanPath(myfile.getOriginalFilename());
        InputStream input=myfile.getInputStream();
        Files.copy(input,path.resolve(filename));

        lst.add(new Emp(id,name,age,filename));

        lst.forEach(System.out::println);
        return "redirect:/ab";
    }

    @RequestMapping("/ab")
    public String hello(Model model){
        lst.stream().forEach(e->{
            System.out.println(e.id+""+e.name+""+e.age);
        });
        model.addAttribute("lst",lst);
        return "view/success";
    }

}
