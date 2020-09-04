
namespace dotnet_microservice_support
{
    public interface IClientFactory
    {
        T Instance<T>() where T : class, new();
    }
}