package www.melody.chat.http;

/**
 * 服务器返回数据的解析基类
 * 这里是测试使用
 * Created by ZhengSheng on 2017/3/20.
 */

public class ApiResponse<T> {

    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
