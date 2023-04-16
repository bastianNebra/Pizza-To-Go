package main

import (
	"fmt"
	"log"
	"net/http"
	"github.com/gorilla/mux"
)

func BaseUrl(w http.ResponseWriter, r *http.Request){
	fmt.Fprintf(w,"Wellcome zu our User Server manager !")
}

func UserLogin(w http.ResponseWriter, r *http.Request){
	fmt.Fprintf(w,"User login")
}

func UserLogout(w http.ResponseWriter, r *http.Request){
	fmt.Fprintf(w,"User login")
}

func UserRegister(w http.ResponseWriter, r *http.Request){
	fmt.Fprintf(w,"User register")
}

func main(){
	r := mux.NewRouter()
	r.HandleFunc("/",BaseUrl)
	r.HandleFunc("/data/createUser/",UserRegister)
	r.HandleFunc("/data/user/login/",UserLogin)
	r.HandleFunc("/data/user/logout/",UserLogout)

	err := http.ListenAndServe(":9000",r)
	if err != nil {
		log.Fatal(err)
	}

}