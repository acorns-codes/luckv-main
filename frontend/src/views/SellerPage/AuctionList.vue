<template>
  <SellerMypageNav />
  <div class="container">
    <div>
      <div>
        <h2>경매 판매내역</h2>
      </div>
      <v-table fixed-header height="600px">
        <thead>
          <tr>
            <th
              v-for="(item, index) in headers"
              :key="index"
              class="text-center"
            >
              {{ item.value }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in desserts" :key="index">
            <td>{{ index + 1 }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.content }}</td>
            <td>{{ item.payMax }}</td>
            <td>{{ item.bidding }}</td>
            <td>{{ item.status }}</td>
          </tr>
        </tbody>
      </v-table>
      <v-row justify="center">
        <v-dialog v-model="dialog" persistent width="500">
          <template v-slot:activator="{ props }">
            <v-btn color="success" v-bind="props"> 경매 내역 추가하기 </v-btn>
          </template>
          <v-card>
            <h3>경매 등록</h3>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      label="제목"
                      v-model="this.title"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-textarea
                      label="내용"
                      v-model="this.content"
                      required
                    ></v-textarea>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field
                      label="경매 시작가"
                      v-model="this.payStart"
                      suffix="원"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field
                      label="시작 날짜"
                      v-model="this.startDay"
                      type="date"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col>
                    <v-text-field
                      label="시작 시간"
                      v-model="this.startTime"
                      type="time"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field
                      label="마감 날짜"
                      v-model="this.lastDay"
                      type="date"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col>
                    <v-text-field
                      label="마감 시간"
                      v-model="this.lastTime"
                      type="time"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="blue-darken-1"
                variant="text"
                @click="dialog = false"
              >
                Close
              </v-btn>
              <v-btn
                color="blue-darken-1"
                variant="text"
                @click="dialog = false"
              >
                Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </div>
  </div>
</template>

<script>
import SellerMypageNav from "@/components/SellerMypageNav.vue";
export default {
  components: { SellerMypageNav },
  data() {
    return {
      itemsPerPage: 5,
      headers: [
        { title: "index", value: "번호" },
        { title: "name", value: "제목" },
        { title: "content", value: "내용" },
        { title: "payMax", value: "최고가" },
        { title: "bidding", value: "입찰가" },
        { title: "status", value: "진행사항" },
      ],
      desserts: [
        {
          name: "Frozen Yogurt",
          content: 159,
          payMax: 6.0,
          bidding: 24,
          status: "1",
        },
        {
          name: "Jelly bean",
          content: 375,
          payMax: 0.0,
          bidding: 94,
          status: "0",
        },
        {
          name: "KitKat",
          content: 518,
          payMax: 26.0,
          bidding: 65,
          status: "6",
        },
        {
          name: "Eclair",
          content: 262,
          payMax: 16.0,
          bidding: 23,
          status: "7",
        },
        {
          name: "Gingerbread",
          content: 356,
          payMax: 16.0,
          bidding: 49,
          protein: 3.9,
          status: "16",
        },
        {
          name: "Ice cream sandwich",
          content: 237,
          payMax: 9.0,
          bidding: 37,
          status: "1",
        },
        {
          name: "Lollipop",
          content: 392,
          payMax: 0.2,
          bidding: 98,
          status: "2",
        },
        {
          name: "Cupcake",
          content: 305,
          payMax: 3.7,
          bidding: 67,
          protein: 4.3,
          status: "8",
        },
        {
          name: "Honeycomb",
          content: 408,
          payMax: 3.2,
          bidding: 87,
          status: "45",
        },
        {
          name: "Donut",
          content: 452,
          payMax: 25.0,
          bidding: 51,
          status: "22",
        },
      ],
      dialog: false,
      title: "",
      content: "",
      payStart: "",
      startDay: "",
      startTime: "",
      lastDay: "",
      lastTime: "",
    };
  },
  methods: {
    registration() {
      console.log("상품등록");
      const validate = this.$refs.form.validate();
      if (validate) {
        if (confirm("새로운 경매를 등록하시겠습니까?")) {
          const videoData = {
            title: this.title,
            content: this.content,
            payStart: this.payStart,
            startDay: this.startDay,
            startTime: this.startTime,
            lastDay: this.lastDay,
            lastTime: this.lastTime,
          };
          console.log(videoData);
        }
      }
    },
  },
};
</script>
<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  & > div {
    width: 1440px;
    padding: 90px;
    & > div:nth-child(1) {
      display: flex;
      justify-content: flex-start;
      padding-bottom: 10px;
    }
  }
}
h3 {
  padding: 20px;
  font-size: 1.5rem;
  padding-bottom: 10px;
}
.add-box {
  /* width: 100%;
  height: 100%;
  position: absolute; */
  z-index: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: rgba(255, 255, 255, 0.496);
}
.form-box {
  width: 500px;
}
.btn-box {
  display: flex;
  justify-content: flex-end;
  & > img {
    width: 10px;
    cursor: pointer;
  }
}
.h3 {
  text-align: center;
}
</style>
