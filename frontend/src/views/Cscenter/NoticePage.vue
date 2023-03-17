<template>
  <CsceterNav />
  <div id="cs-center">
    <h1>공지사항</h1>
    <div class="board-container">
      <v-table>
        <thead>
          <tr style="font-weight: bolder">
            <th class="text-center">번호</th>
            <th class="text-center">제목</th>
            <th class="text-center">작성일</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in contentlist" :key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.title }}</td>
            <td>{{ item.createdAt.split("T")[0] }}</td>
            <!-- Sequelize의 createdAt, updatedAt의 날짜 형식이 '2021-12-10T12:38:52.000Z' 이런 식이여서 
          split('T')[0]을 통해 날짜만 표시 -->
          </tr>
        </tbody>
      </v-table>
    </div>
    <div class="page-box">
      <button @click="movetopreviouspage">
        <!-- 이전페이지로 이동 -->
        <v-icon> mdi-chevron-left </v-icon>
      </button>
      <div>현재페이지 / 총페이지</div>
      <!-- 위와 같이 해줌으로서 '현재페이지/총페이지 page' 식으로 나타냄 -->
      <btton @click="movetonextpage">
        <!-- 다음페이지로 이동 -->
        <v-icon> mdi-chevron-right </v-icon>
      </btton>
    </div>
    <!-- 관리자만 보일 수 있도록 설정 -->
    <v-btn color="success" class="mt-2" @click="movetomain"
      >공지사항 작성</v-btn
    >
  </div>
</template>

<script>
import CsceterNav from "@/components/CsceterNav.vue";
export default {
  components: { CsceterNav },
  data() {
    return {
      contentlist: [
        {
          id: "1",
          title: "제목입니당당당",
          createdAt: "2023-03-17T12:38:52.000Z",
        },
        {
          id: "1",
          title: "제목입니당당당",
          createdAt: "2023-03-17T12:38:52.000Z",
        },
        {
          id: "1",
          title: "제목입니당당당",
          createdAt: "2023-03-17T12:38:52.000Z",
        },
        {
          id: "1",
          title: "제목입니당당당",
          createdAt: "2023-03-17T12:38:52.000Z",
        },
        {
          id: "1",
          title: "제목입니당당당",
          createdAt: "2023-03-17T12:38:52.000Z",
        },
        {
          id: "1",
          title: "제목입니당당당",
          createdAt: "2023-03-17T12:38:52.000Z",
        },
        {
          id: "1",
          title: "제목입니당당당",
          createdAt: "2023-03-17T12:38:52.000Z",
        },
        {
          id: "1",
          title: "제목입니당당당",
          createdAt: "2023-03-17T12:38:52.000Z",
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
#cs-center {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.board-container {
  width: 1020px;
}

.page-box {
  display: flex;
}
</style>
