<template>
  <v-app id="inspire">
    <v-container>
      <v-toolbar color="rgba(0,0,0,0)" flat class="mt-n4">
        <v-spacer></v-spacer>
        <v-btn color="blue" rounded dark @click="signOut">
          Çıkış Yap
        </v-btn>
      </v-toolbar>
      <v-toolbar color="rgba(0,0,0,0)" flat class="mt-n5">
        <v-toolbar-title>ETS Case Çalışması</v-toolbar-title>
        <v-btn color="blue" text  class="ml-5">
          Dosya Yükleme
        </v-btn>
      </v-toolbar>
      <v-item-group mandatory class="mt-n1">
        <v-container>
          <v-row justify="center" class="space">
            <v-col
                cols="6"
                md="2"
            >
              <v-item>
                <v-form @submit.prevent="saveFile" enctype="multipart/form-data">
                  <v-card
                      color="#80A8C4"
                      class="d-flex align-center rounded-xl"
                      dark
                      height="250"
                      width="250"
                  >
                    <v-row>
                      <v-col cols="12" sm="12">
                        <v-list-item three-line  class="mt-10">
                          <v-list-item-content>
                            <div class="mb-4">
                              <v-file-input
                                  type="file"
                                  ref="file"
                                  class="file-input"
                                  v-model="file"
                              />
                            </div>
                          </v-list-item-content>
                        </v-list-item>
                      </v-col>
                    </v-row>
                  </v-card>
                </v-form>
              </v-item>
            </v-col>
          </v-row>
          <v-row justify="center" class="space" style="width: 400px; margin-left: 438px">
            <v-col>
              <v-btn color="blue" dark block tile @click="saveFile">Dosya Kaydet</v-btn>
            </v-col>
            <v-col>
              <v-btn  color="#F07470" dark block tile @click="clear"><i class="fa fa-eraser"></i>Dosya Temizle</v-btn>
            </v-col>
          </v-row>
        </v-container>
      </v-item-group>
      <v-spacer></v-spacer>
      <v-data-table
          ref="refDataTable"
          :headers="headers"
          :items="itemList"
          disable-pagination
      >
        <template slot="item.actions" slot-scope="props">
          <button type="button" class="btn btn-success" @click="downloadFile(props.item)" style=""><i class="fa fa-download"></i></button>
          <button type="button" class="btn btn-warning" @click="deleteFile(props.item)" style="margin-left: 2px"><i class="fa fa-eraser"></i></button>
        </template>
      </v-data-table>
      <v-snackbar v-model="snackbar">
        {{ snackbarText }}
      </v-snackbar>
    </v-container>
  </v-app>
</template>

<script>
import axios from "axios";

export default {
  name: "HomepageView",
  components: {},
  data() {
    return {
      file: null,
      userId: null,
      snackbar: false,
      snackbarText: '',
      itemList: [],
      headers: [
        { text: 'Dosya No', value: 'id' },
        { text: 'Dosya Adı', value: 'fileName' },
        { text: 'İşlemler', value: 'actions'},
      ],
      fileTypes: [
          'application/pdf',
          'text/plain',
          'image/jpeg',
          'image/png',
          'image/jpg',
          'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
          'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
      ]
    }
  },
  mounted() {

  },
  methods:{
    clear() {
      this.file = null
    },
    saveFile() {
      let formData = new FormData();
      formData.append('file', this.file);

      if(this.file.size > 5000000){
        this.snackbarText = 'Yüklemek İstediğiniz Dosya Boyutu Maks. Kabul Edilen(5MB) Dosya Boyutunu Aşmaktadır';
        this.snackbar = true;
      }else{
        let type = [];
        type = this.fileTypes.filter(type => this.file.type.includes(type));
        if(type.length > 0){
          this.save(formData)
        }else{
          this.snackbarText = 'Dosya Tipi Hatası';
          this.snackbar = true;
        }
      }
    },
    save(formData){
      axios.post("http://localhost:8080/file/save-file?" + `userId=${this.userId}`, formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      }).then((response) => {
        if(response && response.data){
          this.snackbarText = 'Dosya Başarıyla Kaydedildi';
          this.snackbar = true;
          this.file = null;
          this.getFiles();
        }else{
          this.snackbarText = 'Dosya Kaydedilirken Bir Hata Oluştu!'
          this.snackbar = true;
          this.file = null;
        }
      });
    },
    downloadFile(data){
      axios.get("http://localhost:8080/file/get-file?" + `id=${data.id}`, {responseType: 'blob'}).then(res => {
        if(res && res.data){
          const blob = new Blob([res.data.data], { type: 'png' })
          const link = document.createElement('a')
          link.href = URL.createObjectURL(blob);
          link.download = res.data.fileName;
          link.click();
          URL.revokeObjectURL(link.href);
        }
      });
    },
    deleteFile(data){
      axios.delete("http://localhost:8080/file/delete-file?" + `id=${data.id}`).then(res => {
        if(res){
          this.snackbarText = 'Dosya Başarıyla Silindi';
          this.snackbar = true;
          this.getFiles();
        }
      });
    },
    signOut(){
      this.$router.push("/")
    },
    getFiles(){
      axios.get("http://localhost:8080/file/find-all-file?" + `userId=${this.userId}&aktif=true`).then(response => {
        if(response && response.data){
          this.itemList = response.data
        }
      });
    }
  },
  created() {
    this.userId = localStorage.getItem("apiLoginResponse");
    if(this.userId){
      this.getFiles();
    }
  }
}
</script>

<style scoped>

</style>