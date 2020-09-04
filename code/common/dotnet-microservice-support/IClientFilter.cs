using System.Collections.Generic;

namespace dotnet_microservice_support
{
    public interface IClientFilter
    {
        Dictionary<string, string> applyHeaders();
    }
}