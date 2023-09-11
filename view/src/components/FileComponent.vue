<template>
  <div class="center">
      <div class="row">
        <div class="col-xl-6">
          <form @submit.prevent="sendFile" enctype="multipart/form-data">
            <div class="file is-boxed is-primary">
              <label class="file-label">
                <v-file-input
                    type="file"
                    ref="file"
                    @change="selectFile"
                    class="file-input"
                />
              </label>
            </div>
            <div class="field" style="width: 300px; margin-top: 20px">
              <button type="button" class="btn btn-primary" @click="sendFile">Dosya Kaydet</button>
              <button type="button" class="btn btn-warning" @click="clear" style="margin-left: 10px"><i class="fa fa-eraser"></i>Dosya Temizle</button>
            </div>
          </form>
        </div>
      </div>
  </div>
</template>

<script>

export default {
  name: "FileComponent",
  components: {},
  data() {
    return {
      file: "",
    }
  },

  methods: {
    selectFile() {
      this.file = this.$refs.file.files[0]
    },
    sendFile() {
      this.$emit('save', this.file)
    },
    clear() {
      this.$refs.file.value = "";

    }
  }
}
</script>

<style scoped>

</style>