<template>
  <div>
    <div>
      <h2>경매 동영상 목록</h2>
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
        <tr
          v-for="(item, index) in auctionList"
          :key="index"
          class="event"
          @click="auctionDetail(item.ano)"
        >
          <td>{{ index + 1 }}</td>
          <td>{{ item.title }}</td>
          <td>{{ item.status }}</td>
        </tr>
      </tbody>
    </v-table>
  </div>
</template>

<script>
export default {
  props: ["auctionList"],
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
        name: "/postauction",
      });
    },
    auctionDetail(ano) {
      console.log(ano);
      console.log("상세");
      this.$router.push({
        name: "auctionDetail",
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
