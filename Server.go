// server.go
package main

import (
	"encoding/json"
	"log"
	"net/http"
)

func dataHandler(w http.ResponseWriter, r *http.Request) {
	var data map[string]interface{}
	if err := json.NewDecoder(r.Body).Decode(&data); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	// Forward data to Python processor
	go forwardToPython(data)
	// Forward data to C++ module
	go forwardToCpp(data)
	w.WriteHeader(http.StatusOK)
}

func main() {
	http.HandleFunc("/data", dataHandler)
	log.Println("Go Server listening on :8080")
	log.Fatal(http.ListenAndServe(":8080", nil))
}
