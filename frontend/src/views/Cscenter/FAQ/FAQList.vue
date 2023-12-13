<template>
  <template v-if="FAQList.length > 0">
    <v-expansion-panels>
      <v-expansion-panel v-for="item in FAQList" :key="item">
        <v-expansion-panel-title>
          <v-row>
            <v-col>{{ item.questions }}</v-col>
          </v-row>
        </v-expansion-panel-title>
        <v-expansion-panel-text>
          <v-expansion-panel-text>{{ item.asked }}</v-expansion-panel-text>
          <v-card-actions
            v-show="this.$store.state.sessionStorageData.auth === 'A'"
          >
            <v-spacer></v-spacer>
            <v-btn variant="text" color="#343434" @click="editFAQ(item.fno)"
              >수정</v-btn
            >
            <v-btn variant="text" color="#343434" @click="deleteFAQ(item.fno)"
              >삭제</v-btn
            >
          </v-card-actions>
        </v-expansion-panel-text>
      </v-expansion-panel>
    </v-expansion-panels>
  </template>
  <template v-else>
    <div class="faq_list">
      <span>준비중입니다.</span>
    </div>
  </template>
</template>

<script>
import { apiDelteFaq } from "@/api/faq";
export default {
  props: ["FAQList"],
  data() {
    return {
      panel: [0, 1],
    };
  },
  methods: {
    editFAQ(fno) {
      this.$router.push({
        name: "editfaq",
        params: { fno: fno },
      });
    },
    async deleteFAQ(fno) {
      const req = {
        fno: fno,
      };
      try {
        const res = await apiDelteFaq(req);
        if (res.data) {
          alert("FAQ가 삭제되었습니다.");
          this.$router.go();
        } else {
          alert("FAQ 삭제를 실패했습니다");
        }
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>

<style scoped>
.faq_list {
  height: 300px;
  display: flex;
  align-items: center;
}
</style>
