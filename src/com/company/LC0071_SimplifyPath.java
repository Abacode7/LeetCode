package com.company;

public class LC0071_SimplifyPath {
    /**
     Given String
     /home/project//../tab/./wild

     String split on /
     "" home project "" .. tab . wild

     We can append directories like as in a stack
     If we encounter the .. directory, we remove the last dir
     in the stack.
     **/

    public String simplifyPath(String path) {
        String[] splitPath = path.split("/");
        StringBuilder sb = new StringBuilder();

        for(String dir: splitPath){
            if(dir.isEmpty() || dir.equals(".")) continue;

            if(dir.equals("..")){
                int lastIndexOfSlash = sb.lastIndexOf("/");
                if(lastIndexOfSlash >= 0)sb.delete(lastIndexOfSlash, sb.length());
            }else{
                sb.append("/");
                sb.append(dir);
            }
        }

        if(sb.toString().isEmpty()) return "/";

        return sb.toString();
    }
}
