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
                    >Yeni Kullanıcı Kaydı</h6>
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
                        <v-btn color="blue" dark block tile @click="save">Kayıt Ol</v-btn>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-col>
                <v-col cols="12" md="6" class="blue rounded-bl-xl" >
                  <div style="  text-align: center; padding: 180px 0;">
                    <v-card-text class="white--text" >
                      <h3 class="text-center ">Bir Hesabınız Var Mı?</h3>
                    </v-card-text>
                    <div class="text-center">
                      <v-btn tile outlined dark @click="signon">Giriş Yap</v-btn>
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
import axios from 'axios';

export default {
  name: 'SignUp',
  data() {
    return {
      snackbar: false,
      snackbarText: '',
      users: {
        username: null,
        password: null,
        admin: false,
        aktif: true
      }
    }
  },
  mounted() {

  },
  methods:{
    save() {
      if(this.users.username !== null && this.users.password !== null){
        axios.post("http://localhost:8080/api/auth/save-user", this.users).then((response) => {
          console.log(response)
          if (response) {
            this.snackbarText = 'Kullanıcı Kaydı Başarılı';
            this.snackbar = true;
            this.users.username = null;
            this.users.password = null;
          }else{
            this.snackbarText = 'Kullanıcı Kaydı Sırasında Bir Hata Oluştu.'
            this.snackbar = true;
          }
        });
      }else{
        this.snackbarText = 'Kullanıcı Adı ve Şifrenizi Giriniz';
        this.snackbar = true;
      }
    },
    signon(){
      this.$router.push("/")
    }
  }
}
</script>