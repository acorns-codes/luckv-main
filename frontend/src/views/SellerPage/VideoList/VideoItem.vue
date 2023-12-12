<template>
  <div>
    <div>
      <h2>{{ type }} 동영상 목록</h2>
    </div>
    <v-table>
      <thead>
        <tr>
          <th v-for="(item, index) in headers" :key="index" class="text-center">
            {{ item.value }}
          </th>
        </tr>
      </thead>
      <tbody>
        <template v-if="auctionList.length > 0">
          <tr
            v-for="(item, index) in auctionList"
            :key="index"
            class="event"
            @click="auctionDetail(item.ano)"
          >
            <td>{{ item.ano }}</td>
            <td>{{ item.title }}</td>
            <td>
              {{
                item.kind === "경매"
                  ? item.status
                  : item.status === "판매중"
                  ? "게시중"
                  : "게시완료"
              }}
            </td>
          </tr>
        </template>
        <template v-else>
          <tr style="height: 200px">
            <td colspan="3">동영상이 없습니다.</td>
          </tr>
        </template>
      </tbody>
    </v-table>
  </div>
</template>

<script>
export default {
  props: ["auctionList", "type"],
  data() {
    return {
      itemsPerPage: 5,
      headers: [
        { title: "index", value: "번호" },
        { title: "title", value: "제목" },
        { title: "status", value: "진행사항" },
      ],
    };
  },

  methods: {
    postAuction() {
      this.$router.push({
        name: "postauction",
      });
    },
    auctionDetail(ano) {
      console.log(ano);
      console.log("상세");
      this.$router.push({
        name: "videoDetail",
        params: { ano: ano },
      });
    },
  },
};
</script>
<style lang="scss" scoped>
button {
  margin: 10px;
}

.event:hover {
  /* font-weight: bold; */
  background-color: #eee;
  cursor: pointer;
}
</style>
