<template>
  <v-container>
    <v-row align="center" justify="center" >
      <v-col cols="12" sm="10">
        <v-card class="elevation-6 mt-10"  >
          <v-window>
            <v-window-item :value="1">
              <v-row>
                <v-col cols="12" md="6">
                  <v-card-text class="mt-12">
                    <h4
                        class="text-center"
                    >ETS Case Çalışması</h4>
                    <h6
                        class="text-center  grey--text "
                    >Giriş Yapmak İçin Kullanıcı Adı ve Şifrenizi Giriniz</h6>
                    <v-row align="center" justify="center">
                      <v-col cols="12" sm="8">

                        <v-text-field
                            label="Kullanıcı Adı"
                            outlined
                            dense
                            color="blue"
                            autocomplete="false"
                            class="mt-16"
                            v-model="users.username"
                        />
                        <v-text-field
                            label="Şifre"
                            outlined
                            dense
                            color="blue"
                            autocomplete="false"
                            type="password"
                            v-model="users.password"
                        />
                        <v-row>
                          <v-col cols="12" sm="7">
                            <v-checkbox
                                label="Beni Hatırla"
                                class="mt-n1"
                                color="blue"
                            > </v-checkbox>
                          </v-col>
                          <v-col cols="12" sm="5">
                            <span class="caption blue--text">Şifremi Unuttum</span>
                          </v-col>
                        </v-row>
                        <v-btn color="blue" dark block tile @click="login">Giriş Yap</v-btn>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-col>
                <v-col cols="12" md="6" class="blue rounded-bl-xl" >
                  <div style="  text-align: center; padding: 180px 0;">
                    <v-card-text class="white--text" >
                      <h3 class="text-center ">Henüz Bir Hesabınız Yok Mu?</h3>
                    </v-card-text>
                    <div class="text-center">
                      <v-btn tile outlined dark @click="signup">Kayıt Ol</v-btn>
                    </div>
                  </div>
                </v-col>
              </v-row>
            </v-window-item>
          </v-window>
        </v-card>
      </v-col>
    </v-row>
    <v-snackbar v-model="snackbar">
      {{ snackbarText }}
    </v-snackbar>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: 'LoginView',
  data() {
    return {
      snackbar: false,
      snackbarText: '',
      users: {
        username: null,
        password: null,
      }
    }
  },
  mounted() {

  },
  methods:{
    login() {
      if(this.users.username !== null && this.users.password !== null){
        axios.post("http://localhost:8080/api/auth/get-user", this.users).then((response) => {
          if(response && response.data.id){
            localStorage.setItem('apiLoginResponse', response.data.id)
            this.$router.push("/anasayfa")
          }
        });
      }else{
        this.snackbarText = 'Kullanıcı Adı ve Şifrenizi Giriniz';
        this.snackbar = true;
      }

    },
    signup(){
      this.$router.push("/kayıt-ol")
    },
  }
}
</script>