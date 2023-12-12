<template>
  <div class="container">
    <div class="sub-list" v-if="this.userData.subYn === 'Y'">
      <h2>구독 내역</h2>
      <div>
        <div>
          <v-table class="table-box">
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
              <tr>
                <td>구독중</td>
                <td>구독 동영상 1개월 구독권</td>
                <td>{{ this.userData.subStartDay }}</td>
                <td>{{ this.userData.subLastDay }}</td>
              </tr>
            </tbody>
          </v-table>
          <div class="btn-box">
            <v-btn @click="subNot">해지</v-btn>
            <v-btn @click="sub">연장</v-btn>
          </div>
        </div>
      </div>
    </div>
    <template v-else>
      <v-sheet class="card-box" elevation="4">
        <div>
          <h2>구독내역이 없습니다!</h2>
          <p>
            구독을 하시게 되면 구독에 있는 동영상을 자유롭게 사용하실 수
            있습니다.
          </p>
          <v-btn variant="text" color="orange" @click="sub">구독하기</v-btn>
        </div>
      </v-sheet>
    </template>
  </div>
</template>

<script>
import { apiPostVideo, apiDeleteVideo, apiGetInfoMember } from "@/api/user";
export default {
  data() {
    return {
      sessionData: this.$store.state.sessionStorageData,
      userData: "",
      headers: [
        { title: "status", value: "상태" },
        { title: "name", value: "구독" },
        { title: "startDay", value: "시작일" },
        { title: "lastDay", value: "만료일" },
      ],
    };
  },
  mounted() {
    this.getInfo();
    console.log("구독중인지확인", this.$store.state.subAuth);
  },
  methods: {
    // 현재 시간 구하기
    getDate() {
      const now = new Date();
      console.log(now);
      const year = now.getFullYear();
      const month =
        now.getMonth() + 1 < 10 ? `0${now.getMonth() + 1}` : now.getMonth() + 1;
      const date = now.getDate() < 10 ? `0${now.getDate()}` : now.getDate();
      const hour = now.getHours();
      const minutes =
        now.getMinutes() < 10 ? `0${now.getMinutes()}` : now.getMinutes();
      const seconds =
        now.getSeconds() < 10 ? `0${now.getSeconds()}` : now.getSeconds();
      const day = `${year}-${month}-${date}`;
      const time = `${hour}:${minutes}:${seconds}`;
      const today = `${day} ${time}`;
      return today;
    },
    // 회원 정보 불러오기
    async getInfo() {
      console.log("구독 정보 불러오기");
      const req = {
        mno: this.sessionData.mno,
      };
      try {
        const res = await apiGetInfoMember(req);
        console.log(res);
        this.userData = res.data;
        console.log(this.userData.subLastDay, "마감날자아아아아아아아");
        console.log(this.userData.subStartDay);
        console.log(this.userData);
      } catch (error) {
        console.log(error);
      }
    },
    // 구독신청
    async sub() {
      console.log(this.$store.state.subAuth);
      const newData = {
        mno: this.$store.state.sessionStorageData.mno,
        subStartDay: this.userData.subLastDay === null ? null : this.getDate(),
        subLastDay:
          this.userData.subLastDay === null ? null : this.userData.subLastDay,
      };
      console.log(newData.subLastDay);
      if (!confirm("구독을 신청하시겠습니까?")) {
        alert("구독 신청이 완료되지 못했습니다!");
      } else {
        try {
          const res = await apiPostVideo(newData);
          if (res.data) {
            alert("구독 신청이 완료되었습니다!");
            this.$store.commit("storeSubAuth", "Y");
            this.getInfo();
          }
        } catch (error) {
          console.log(error);
        }
      }
    },
    // 구독해지
    async subNot() {
      const req = {
        mno: this.$store.state.sessionStorageData.mno,
      };
      try {
        const res = await apiDeleteVideo(req);
        console.log(res);
        if (res) {
          alert("구독 해지 신청이 완료되었습니다!");
          this.$store.commit("storeSubAuth", "N");
          this.getInfo();
        }
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  padding-top: 110px;
  & > h2 {
    margin-bottom: 50px;
  }

  & > div {
    width: 100%;
    padding-bottom: 10px;
  }
}

.table-box {
  width: 100%;
}

.btn-box {
  button {
    margin: 50px 10px;
  }
}

.card-box {
  width: 600px;
  height: 250px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  h2 {
    color: orange;
    font-weight: bold;
    font-size: 2rem;
    margin-bottom: 15px;
  }
  p {
    font-size: 0.9rem;
  }
  button {
    margin: 10px;
  }
}
</style>
