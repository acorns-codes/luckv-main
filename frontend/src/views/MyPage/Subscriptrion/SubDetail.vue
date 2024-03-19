<template>
  <div class="container">
    <div class="sub-list" v-if="this.sessionStorageData.subYn === 'Y'">
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
                <td>{{ this.sessionStorageData.subStartDay }}</td>
                <td>{{ this.sessionStorageData.subLastDay }}</td>
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
import { apiPostVideo, apiDeleteVideo } from "@/api/user";

export default {
  data() {
    return {
      headers: [
        { title: "status", value: "상태" },
        { title: "name", value: "구독" },
        { title: "startDay", value: "시작일" },
        { title: "lastDay", value: "만료일" },
      ],
    };
  },

  computed: {
    sessionStorageData() {
      return this.$store.state.sessionStorageData;
    },
  },
  methods: {
    // 현재 시간 구하기
    getDate() {
      const now = new Date();
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

    // 구독신청
    async sub() {
      let req;
      if (this.sessionStorageData.subYn === "Y") {
        req = {
          mno: this.sessionStorageData.mno,
          subStartDay:
            this.sessionStorageData.subLastDay === null ? null : this.getDate(),
          subLastDay:
            this.sessionStorageData.subLastDay === null
              ? null
              : this.sessionStorageData.subLastDay,
        };
      } else {
        req = {
          mno: this.sessionStorageData.mno,
        };
      }
      let _confirm;
      if (this.sessionStorageData.subYn === "Y") {
        _confirm = confirm("구독을 연장하시겠습니까?");
      } else {
        _confirm = confirm("구독을 신청하시겠습니까?");
      }

      if (!_confirm) {
        return;
      }

      try {
        const res = await apiPostVideo(req);
        if (res.msg === "SUCCESS") {
          this.refreshUserInfo();
          alert("구독 신청 완료");
        }
      } catch (error) {
        console.error(error);
      }
    },

    // 구독해지
    async subNot() {
      const _confirm = confirm("구독을 해지하시겠습니까?");
      if (!_confirm) {
        return;
      }
      const req = {
        mno: this.sessionStorageData.mno,
      };

      try {
        const res = await apiDeleteVideo(req);
        if (res) {
          alert("구독 해지 신청이 완료되었습니다!");
          this.refreshUserInfo();
        }
      } catch (error) {
        console.error(error);
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
