using System;
using System.Collections.Generic;
using System.Reflection;

namespace dotnet_microservice_support
{
    public class ClientFactory: IClientFactory
    {
        List<IClientFilter> clientFilters = new List<IClientFilter>();


        public T Instance<T>() where T : class, new()
        {
            T serviceClient = new T();
            clientFilters.ForEach((clientFilter)=>
            {
                
                MethodInfo methodInfo = serviceClient.GetType().GetMethod("AddClientFilter");

                Func<Dictionary<String, String>> fun = () =>
                {
                    return clientFilter.applyHeaders();
                };

                
                methodInfo.Invoke(serviceClient, new object[]{fun});

            });
            
            return serviceClient;
        }

        public void register(IClientFilter clientFilter)
        {
            clientFilters.Add(clientFilter);
        }
    }
}