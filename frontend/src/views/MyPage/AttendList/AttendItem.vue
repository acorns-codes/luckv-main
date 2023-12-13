<template>
  <h2>경매 낙찰 내역</h2>
  <v-table>
    <thead>
      <tr>
        <th v-for="(item, index) in headers" :key="index" class="text-center">
          {{ item.value }}
        </th>
      </tr>
    </thead>
    <tbody v-if="attendList.length > 0">
      <tr
        v-for="(item, index) in attendList"
        :key="index"
        class="event"
        @click="attendDetail(item.ano)"
      >
        <td>{{ index + 1 }}</td>
        <td>{{ item.title }}</td>
        <td>{{ $globalFuc(item.payMax) }}</td>
        <td>{{ item.status }}</td>
      </tr>
    </tbody>
    <tbody v-else>
      <tr style="height: 200px">
        <td colspan="4">동영상이 없습니다.</td>
      </tr>
    </tbody>
  </v-table>
</template>

<script>
export default {
  props: ["attendList"],
  data() {
    return {
      headers: [
        { title: "index", value: "번호" },
        { title: "name", value: "제목" },
        { title: "payMax", value: "낙찰가" },
        { title: "status", value: "진행사항" },
      ],
    };
  },
  methods: {
    attendDetail(ano) {
      this.$router.push({
        name: "mydetailListdetail",
        params: { ano: ano },
      });
    },
  },
};
</script>

<style>
.event:hover {
  /* font-weight: bold; */
  background-color: #eee;
  cursor: pointer;
}
</style>
