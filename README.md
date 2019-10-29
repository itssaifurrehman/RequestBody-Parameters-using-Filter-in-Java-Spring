# RequestBody-Parameters-using-Filter-in-Java-Spring
Simple Java Code to read InputStream once and fetch the RequestBody Parameters.

There are two files in the project. One is the HttpServletCacheWrapper and other one is RequestResponseLoggingFilter.

HttpServletCacheWrapper is used to read the inputStream multiple times. When you try to read it Using getReader() or getInputStream(), you get errors because you can not read them multiple times. Even if you want to read it for once you will get a lot of errors. After 2 days of hardwork in getting this done, I came up with the solution and it is in the attached files. You dont need any other HttpServletRequest. Just use these two files and your work is done.