using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace dotnet_microservice_support
{

    public class OpenTracingHeaders
    {
        internal string Request;
        internal string Traceid;
        internal string Spanid;
        internal string Parentspanid;
        internal string Sampled;
        internal string Flags;
        internal string Scontext;

        
        public OpenTracingHeaders(HttpContext context)
        {
            Request = context.Request.Headers["x-request-id"];
            Traceid = context.Request.Headers["x-b3-traceid"];
            Spanid = context.Request.Headers["x-b3-spanid"];
            Parentspanid = context.Request.Headers["x-b3-parentspanid"];
            Sampled = context.Request.Headers["x-b3-sampled"];
            Flags = context.Request.Headers["x-b3-flags"];
            Scontext = context.Request.Headers["x-ot-span-context"];
        }

        internal Dictionary<string, string> GetOpenTracingHeaders()
        {
            Dictionary<string, string> headers = new Dictionary<string, string>();
            headers.Add("x-request-id", Request);
            headers.Add("x-b3-traceid", Traceid);
            headers.Add("x-b3-spanid", Spanid);
            headers.Add("x-b3-parentspanid", Parentspanid);
            headers.Add("x-b3-sampled", Sampled);
            headers.Add("x-b3-flags", Flags);
            headers.Add("x-ot-span-context", Scontext);
            return headers;
        }

    }
}