using System.Collections.Generic;

namespace dotnet_microservice_support
{
    public class OpenTracingClientFilter : IClientFilter
    {
        public Dictionary<string, string> applyHeaders()
        {
            return OpenTracingFilter.ThreadLocal.Value.GetOpenTracingHeaders();
        }
    }
}