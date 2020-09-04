using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace dotnet_microservice_support
{


    public class OpenTracingFilter
    {
        public static ThreadLocal<OpenTracingHeaders> ThreadLocal = new ThreadLocal<OpenTracingHeaders>();

        RequestDelegate _next;
        public OpenTracingFilter(RequestDelegate next)
        {
            _next = next;
        }

 

        public async Task Invoke(HttpContext context)
        {
            
            ThreadLocal.Value = new OpenTracingHeaders(context);

            await _next(context);
        }
        
    }
}   