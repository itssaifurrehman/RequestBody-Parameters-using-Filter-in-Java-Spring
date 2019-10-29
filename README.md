# RequestBody-Parameters-using-Filter-in-Java-Spring
Simple Java Code to read InputStream once and fetch the RequestBody Parameters.

There are two files in the project. One is the HttpServletCacheWrapper and other one is RequestResponseLoggingFilter.
HttpServletCacheWrapper is used to read the inputStream multiple times. When you try to read it using getReader() or getInputStream(), you get errors because you can not read them multiple times. Even if you want to read it for once you will get a lot of errors. After 2 days of hardwork in getting this done, I came up with the solution and it is in the attached files. You dont need any other HttpServletRequest. Just use these two files and your work is done.

Solution:
It's not enough to just redefine parameter accessing methods. Several things must be done.
A filter is needed where request will be wrapped.
A custom HttpRequestWrapper is needed with all parameter access methods overridden. Request body should be parsed in constructor and stored as a field.
getInputStream and getReader methods should be redefined as well. They return values depend on the stored request body.
Custom class extending ServletInputStream is required since this one is abstract.
This 4 combined will allow you to use getParameter without interference with getInputStream and getReader methods.

Mind that manual request parameter parsing may get complicated with multipart requests. But that's another topic.
