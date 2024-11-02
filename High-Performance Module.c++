// module.cpp
#include <httplib.h>
#include <nlohmann/json.hpp>

using json = nlohmann::json;

void process_data(const json& data) {
    // Perform intensive computation
    json result = {{"computation", "result"}};
    httplib::Client cli("localhost", 8080);
    cli.Post("/result", result.dump(), "application/json");
}

int main() {
    httplib::Server svr;

    svr.Post("/compute", [](const httplib::Request& req, httplib::Response& res) {
        auto data = json::parse(req.body);
        process_data(data);
        res.set_content("Processed", "text/plain");
    });

    std::cout << "C++ Module running on port 9000" << std::endl;
    svr.listen("0.0.0.0", 9000);
}
