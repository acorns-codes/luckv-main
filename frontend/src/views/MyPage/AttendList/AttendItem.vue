<template>
  <h2>참여내역</h2>
  <v-table>
    <thead>
      <tr>
        <th v-for="(item, index) in headers" :key="index" class="text-center">
          {{ item.value }}
        </th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, index) in attendList" :key="index">
        <td>{{ index + 1 }}</td>
        <td>{{ item.title }}</td>
        <td>{{ item.payMax }}</td>
        <td>{{ item.bidding }}</td>
        <td>{{ item.status }}</td>
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
        { title: "payMax", value: "최고가" },
        { title: "bidding", value: "입찰가" },
        { title: "status", value: "진행사항" },
      ],
    };
  },
};
</script>

<style></style>
