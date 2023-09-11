import Vue from 'vue'
import VueI18n from 'vue-i18n'

Vue.use(VueI18n)


// Html Header Codes
const html_codes = {
  en: 'en-US',
  tr: 'tr-TR'
}

// Date Settings
const dateTimeFormats = {
  'en': {
    short: { year: 'numeric', month: 'short', day: 'numeric' },
    long: {
      year: 'numeric', month: 'short', day: 'numeric',
      weekday: 'short', hour: 'numeric', minute: 'numeric'
    }
  },
  'tr': {
    short: { year: 'numeric', month: 'short', day: 'numeric' },
    long: {
      year: 'numeric', month: 'short', day: 'numeric',
      weekday: 'short', hour: 'numeric', minute: 'numeric'
    }
  }
}

// Currency Settings
const numberFormats = {
  'en': { currency: { style: 'currency', currency: 'USD' }},
  'tr': { currency: { style: 'currency', currency: 'TRY' }}
}


const i18n = new VueI18n({
  numberFormats,
  dateTimeFormats,
  locale: $e.VUE_APP_LANG,
  silentTranslationWarn: false
})

export function setI18nLanguage(lang){
  i18n.locale = lang
  localStorage.setItem('lang', lang)
  document.querySelector('html').setAttribute('lang', lang)
  return lang
}

export function getActiveLang(){
  return localStorage.getItem('lang') || $e.VUE_APP_LANG
}

export function getHTMLHeaderCode(){
  return html_codes[getActiveLang()]
}

export function translate(label){
  return i18n.t(label);
}

export function loadLanguageAsync (lang) {
  if (i18n.locale !== lang) {
    if (!loadedLanguages.includes(lang)) {
      return import(/* webpackChunkName: "lang-[request]" */ `@/lang/${lang}`).then(msgs => {
        i18n.setLocaleMessage(lang, msgs.default)
        loadedLanguages.push(lang)
        return setI18nLanguage(lang)
      })
    }
    return Promise.resolve(setI18nLanguage(lang))
  }
  return Promise.resolve(lang)
}

export default i18n
