<template>
  <CsceterNav />
  <div class="cscenter">
    <h1>This is an faq page</h1>
    <div>
      <v-expansion-panels v-for="item in FAQList" :key="item">
        <v-expansion-panel :title="`${item.questions}`" :text="`${item.asked}`">
        </v-expansion-panel>
      </v-expansion-panels>
    </div>
  </div>
</template>

<script>
import CsceterNav from "@/components/CsceterNav.vue";
export default {
  components: { CsceterNav },
  data() {
    return {
      panel: [0, 1],
      FAQList: this.$store.state.FAQList,
    };
  },
  methods: {
    async getFAQ() {
      console.log("faq불러오기");
      try {
        const res = await this.$axios({
          method: "GET",
          url: `http://localhost:80/frequentlyList`,
        });
        console.log(res);
        this.$store.commit("getFAQList", res.data);
        console.log(this.$store.state.FAQList);
      } catch (error) {
        console.log(error);
      }
    },
  },
  created() {
    this.getFAQ();
  },
};
</script>
